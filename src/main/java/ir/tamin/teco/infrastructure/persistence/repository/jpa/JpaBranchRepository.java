package ir.tamin.teco.infrastructure.persistence.repository.jpa;

import ir.tamin.teco.infrastructure.persistence.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaBranchRepository extends JpaRepository<BranchEntity, String>, JpaSpecificationExecutor<BranchEntity> {

    Optional<BranchEntity> findByBrhCode(String code);

   // List<BranchEntity> search(/* search filters and pagination and sort in app/model/query -> domain/model/search*/);

}