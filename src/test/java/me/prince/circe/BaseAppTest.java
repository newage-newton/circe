package me.prince.circe;

import me.prince.circe.config.AppConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = AppConfig.class)
@Transactional(transactionManager = "transactionManager")
@Rollback
@ExtendWith(SpringExtension.class)
public abstract class BaseAppTest {
}
