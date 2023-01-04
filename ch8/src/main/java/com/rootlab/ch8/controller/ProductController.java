package com.rootlab.ch8.controller;

import com.rootlab.ch8.data.dto.ChangeProductNameDto;
import com.rootlab.ch8.data.dto.ProductRequestDto;
import com.rootlab.ch8.data.dto.ProductResponseDto;
import com.rootlab.ch8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public ResponseEntity<ProductResponseDto> getProduct(Long number) {
		ProductResponseDto productResponseDto = productService.getProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}

	@PostMapping()
	public ResponseEntity<ProductResponseDto> postProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponseDto = productService.saveProduct(productRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
	}

	@PutMapping()
	public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductNameDto changeProductNameDto) {
		ProductResponseDto productResponseDto = productService.changeProductName(
				changeProductNameDto.getNumber(),
				changeProductNameDto.getName()
		);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponseDto);
	}

	@DeleteMapping(produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> deleteProduct(Long number) {
		productService.deleteProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}

}

