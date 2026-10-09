package com.example.survey.form;

import java.util.List;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SurveyForm {
	
	private Integer storeId;
	
	private String storeName;

	@NotBlank(message = "名前を入力してください")
	@Size(max = 20)
	private String name;
	@NotNull(message = "年齢層を選択してください")
	private Integer age;
	@NotEmpty(message = "来店目的を1つ以上選択してください")
	private List<Integer> purpose;
	@NotNull(message = "来店頻度を選択してください")
	private Integer frequency;
	@NotNull(message = "接客の満足度を選択してください")
	@Min(1) @Max(5)
	private Integer food;
	@NotNull(message = "接客の満足度を選択してください")
	@Min(1) @Max(5)
	private Integer service;
	@NotNull(message = "店内の清潔感を選択してください")
	@Min(1) @Max(5)
	private Integer cleanliness;
	@Size(max = 100, message = "100文字以内で入力してください")
	private String menu;
	private Boolean hamburgOrder;
	private Integer hamburgSatisfaction;
	@Size(max = 200, message = "200文字以内で入力してください")
	private String comment;
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	@AssertTrue(message = "利用規約に同意してください")
	private Boolean agreementFlg;
}