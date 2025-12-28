package com.Adily.Project.demo.Repository;

import com.Adily.Project.demo.Entity.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BookingRepo  extends JpaRepository<BookingInfo,Integer> {
    List<BookingInfo> findByStatus (String status);
    @Modifying
    @Query("UPDATE BookingInfo as b " +
            "SET b.status = 'ACCEPTED'" +
            " WHERE b.bookingId = ?1" +
            "  AND b.status = 'REQUESTED'" +
            "  AND b.expiryTime > CURRENT_TIMESTAMP")
    int updatebooking( int bookid);

}
