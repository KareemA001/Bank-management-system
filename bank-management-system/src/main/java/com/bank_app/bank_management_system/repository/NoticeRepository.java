package com.bank_app.bank_management_system.repository;

import com.bank_app.bank_management_system.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = """
    from Notice n
    where CURRENT_DATE BETWEEN n.noticBegDt AND n.noticEndDt
    """)
    List<Notice> findAllActiveNotices();
}
