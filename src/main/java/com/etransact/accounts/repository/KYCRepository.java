package com.etransact.accounts.repository;


import com.etransact.accounts.entity.KYCDocument;
import com.etransact.accounts.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KYCRepository extends PagingAndSortingRepository<KYCDocument, String>, JpaSpecificationExecutor<KYCDocument> {
}