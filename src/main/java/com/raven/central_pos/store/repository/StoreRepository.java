package com.raven.central_pos.store.repository;

import com.raven.central_pos.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long > {
    Store findByStoreAdminId(Long adminId);

}
