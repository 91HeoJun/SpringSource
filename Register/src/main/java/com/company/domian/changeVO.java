package com.company.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class changeVO {

	private String userid;
	private String password;
	private String new_password;
	private String confirm_password;

	public boolean newPasswordEqualsconfirmPassword() {
		return new_password.equals(confirm_password);
	}
}