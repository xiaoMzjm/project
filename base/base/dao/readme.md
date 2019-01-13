# spring-data jpa使用指南：
## 1、引入依赖
<!-- ================================================= -->
<!-- mysql -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<!-- spring jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

## 2、在main上配置注解
@EnableJpaRepositories("com.zjm.dao.repository")
@EntityScan("com.zjm.dao.model")

## 3、写带注解的DO
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // 省略get & set
}

## 4、写Repository
@Repository
public interface UserRepository extends JpaRepository<UserDO,Long> {
}

