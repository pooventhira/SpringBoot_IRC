package poovie.cc1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poovie.cc1.model.CartModel;
import poovie.cc1.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController 
{
    @Autowired
    private CartService serv;

    @PostMapping("/createProduct")
    public ResponseEntity<CartModel> createproduct(@NonNull @RequestBody CartModel product) {
        CartModel createdproduct = serv.createNewproduct(product);
        return new ResponseEntity<>(createdproduct, HttpStatus.CREATED);
    }

    @GetMapping("/readAllProducts")
    public ResponseEntity<List<CartModel>> getAllcart() {
        List<CartModel> cart = serv.getAllProducts();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @GetMapping("/readProduct/{productName}")
    public ResponseEntity<?> getproductByproductName(@PathVariable String productName) {
        Optional<CartModel> product = serv.getProductByName(productName);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updateProduct/{productName}")
    public ResponseEntity<CartModel> updateproduct(@NonNull @PathVariable String productName,@RequestBody CartModel updateRequest) {
    CartModel updated = serv.updatProduct(productName, updateRequest);
    return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<Void> removeUser(@NonNull @PathVariable Integer productId) {
        serv.removeUser(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}