package poovie.cc1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poovie.cc1.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository <CartModel, Integer>
{
    Optional<CartModel> findByProductName(String productName);
}