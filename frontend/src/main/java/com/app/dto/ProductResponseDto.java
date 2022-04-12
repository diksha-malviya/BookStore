package com.app.dto;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

	int id;
	private String productName;
	private String categoryName;
	private int categoryId;
	private int quantity;
	private double price;
	private String description;
	private String imageName;
	private String  type;
	private byte[] data;
	@Override
	public String toString() {
		return "ProductResponseDto [productName=" + productName + ", categoryName=" + categoryName + ", quantity="
				+ quantity + ", price=" + price + ", description=" + description + ", imageName=" + imageName
				+ ", type=" + type + ", data=" + Arrays.toString(data) + "]";
	}

}
