package com.vpavlenko.restTest.messagedao;

import com.vpavlenko.restTest.model.TableMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<TableMessage, Long> {


}
