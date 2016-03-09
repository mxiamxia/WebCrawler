package com.min.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.min.model.Item;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
public class ItemFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Item.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Item user = (Item) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item_id", "NotEmpty.itemForm.item_id");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale_price", "NotEmpty.itemForm.sale_price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "NotEmpty.itemForm.size");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "NotEmpty.itemForm.color");

//		if(user.getNumber()==null || user.getNumber()<=0){
//			errors.rejectValue("number", "NotEmpty.userForm.number");
//		}

	}
}