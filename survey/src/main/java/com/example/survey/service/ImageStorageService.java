@Service
public class ImageStorageService {
    private static final Set<String> ALLOWED =
        Set.of("image/jpeg", "image/png", "image/webp");
    private static final long MAX_BYTES = 5L * 1024 * 1024;

    @Value("${supabase.url}") private String supabaseUrl;
    @Value("${supabase.secret-key}") private String secretKey;
    @Value("${supabase.bucket}") private String bucket;

    private final HttpClient client = HttpClient.newHttpClient();

    /** 保存して、バケット内のパスを返す(DBにはこれを保存する) */
    public String upload(MultipartFile file) throws IOException, InterruptedException {
        String type = file.getContentType();
        if (type == null || !ALLOWED.contains(type)) {
            throw new IllegalArgumentException("jpeg / png / webp のみアップロードできます");
        }
        if (file.getSize() > MAX_BYTES) {
            throw new IllegalArgumentException("5MB以下の画像にしてください");
        }
        String ext = switch (type) {
            case "image/png" -> "png";
            case "image/webp" -> "webp";
            default -> "jpg";
        };
        String path = UUID.randomUUID() + "." + ext;   // 元のファイル名は使わない

        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(supabaseUrl + "/storage/v1/object/" + bucket + "/" + path))
            .header("apikey", secretKey)
            .header("Content-Type", type)
            .POST(HttpRequest.BodyPublishers.ofByteArray(file.getBytes()))
            .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() / 100 != 2) {
            throw new IOException("アップロード失敗: " + res.statusCode() + " " + res.body());
        }
        return path;
    }

    /** 公開バケットの表示用URL */
    public String publicUrl(String path) {
        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + path;
    }
}
