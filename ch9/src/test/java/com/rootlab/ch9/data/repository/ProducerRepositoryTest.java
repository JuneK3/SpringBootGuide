package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Producer;
import com.rootlab.ch9.data.entity.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class ProducerRepositoryTest {

	@Autowired
	ProducerRepository producerRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	// @Transactional로 영속성 컨텍스트가 유지되지 않으면 lazy fetch로 데이터를 가져올 수 없어 테스트가 실패함
	// Eager fetch로 데이터를 가져오거나 @Transactional로 영속성 컨텍스트를 유지시켜주어야 함
	public void relationshipTest1() {
		Product product1 = saveProduct("동글펜", 500, 1000);
		Product product2 = saveProduct("네모 공책", 100, 2000);
		Product product3 = saveProduct("지우개", 152, 1234);

		Producer producer1 = saveProducer("홍익공장");
		Producer producer2 = saveProducer("마포공장");

		producer1.addProduct(product1);
		producer1.addProduct(product2);
		producer2.addProduct(product2);
		producer2.addProduct(product3);

		producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
		Producer foundProducer = producerRepository.findById(producer1.getId()).orElseThrow(RuntimeException::new);

		System.out.println(foundProducer.getProducts());
	}

	@Test
	@Transactional
	void relationshipTest2() {
		Product product1 = saveProduct("동글펜", 500, 1000);
		Product product2 = saveProduct("네모 공책", 100, 2000);
		Product product3 = saveProduct("지우개", 152, 1234);

		Producer producer1 = saveProducer("홍익공장");
		Producer producer2 = saveProducer("마포공장");

		producer1.addProduct(product1);
		producer1.addProduct(product2);
		producer2.addProduct(product2);
		producer2.addProduct(product3);

		product1.addProducer(producer1);
		product2.addProducer(producer1);
		product2.addProducer(producer2);
		product3.addProducer(producer2);

		producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
		productRepository.saveAll(Lists.newArrayList(product1, product2, product3));
		Producer foundProducer = producerRepository.findById(producer1.getId()).orElseThrow(RuntimeException::new);
		Product foundProduct = productRepository.findById(product2.getNumber()).orElseThrow(RuntimeException::new);

		System.out.println("products : " + foundProducer.getProducts());
		System.out.println("producers : " + foundProduct.getProducers());
	}

	private Product saveProduct(String name, Integer price, Integer stock) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		return productRepository.save(product);
	}

	private Producer saveProducer(String name) {
		Producer producer = new Producer();
		producer.setName(name);
		return producerRepository.save(producer);
	}

}