package me.prince.circe.dao;

import me.prince.circe.config.DaoConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = DaoConfig.class)
@Transactional(transactionManager = "transactionManager")
@Rollback
@ExtendWith(SpringExtension.class)
public abstract class BaseDaoTest {
}
