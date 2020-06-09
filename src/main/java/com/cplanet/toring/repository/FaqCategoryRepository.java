package com.cplanet.toring.repository;

import com.cplanet.toring.domain.FaqCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//import org.springframework.data.repository.query.Param;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> {


    List<FaqCategory> findAllByOrderByOrderingDescCreateDateDesc();

    @Query(value = "select c from FaqCategory c " +
            "join c.faqInfos f " +
            "where c.displayStatus = 'OK' " +
            "and f.displayStatus = 'OK' " +
            "order by c.ordering desc, f.ordering desc ",
            countQuery = "select count(c) from FaqCategory c " +
                    "join  c.faqInfos f " +
                    "where c.displayStatus = 'OK' " +
                    "  and  f.displayStatus = 'OK' ")
    Page<FaqCategory> findAll(Pageable page);


    @Query(value = "select c from FaqCategory c " +
            "join c.faqInfos f " +
            "where c.displayStatus = 'OK' " +
            "and f.displayStatus = 'OK' " +
            "and (f.question like %:keyword% or f.answer like %:keyword%)" +
            "order by c.ordering desc, f.ordering desc ")
    List<FaqCategory> findByKeywordWithoutPage(@Param(value = "keyword") String keyword);


    // Page 처리는 주 테이블의 Row수 기준으로 처리를 한다. 따라서, coutnQuery에서는 join fetch를 통한 Fetch 작업이 필요 없다.
    @Query(value = "select c from FaqCategory c " +
            "join c.faqInfos f " +
            "where c.displayStatus = 'OK' " +
            "and f.displayStatus = 'OK' " +
            "and (f.question like %:keyword% or f.answer like %:keyword%)" +
            "order by c.ordering desc, f.ordering desc ",
            countQuery =  "select count(f) from FaqCategory c join  c.faqInfos f " +
                    "where c.displayStatus = 'OK' " +
                    " and (f.question like %:keyword% or f.answer like %:keyword%)" +
                    "  and  f.displayStatus = 'OK' ")
//            countQuery= "select count(f) from FaqInfo  f " +
//                        " where f.displayStatus = 'OK' " +
//                        " and (f.question like %:keyword% or f.answer like %:keyword%)")
    Page<FaqCategory> findAllByKeyword(@Param(value = "keyword") String keyword, Pageable page);



    @Query(value = "select count(f) from FaqCategory c join c.faqInfos f " +
                    "where c.displayStatus = 'OK' " +
                    " and (f.question like %:keyword% or f.answer like %:keyword%)" +
                    "  and  f.displayStatus = 'OK' ")
    int findByKeyword(@Param(value = "keyword") String keyword);


}
