package com.rick.webservice.rick;

import com.rick.webservice.rick.UserRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestLogRepository extends JpaRepository<UserRequestLog, Long> {}