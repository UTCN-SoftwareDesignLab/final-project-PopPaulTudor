package com.lab4.demo.gather;

import com.lab4.demo.gather.model.Gather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherRepository extends JpaRepository<Gather, Long> {


}
