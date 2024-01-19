package com.ascenda.hotels_data_merge.repository;

import com.ascenda.hotels_data_merge.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Book, Long> {
}
