## @DataSource注解（多数据源切换）

```java
/**
 * 自定义多数据源切换注解
 * <p>
 * 优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 *
 * @author tienchin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
```

在配置文件中可以配置其他数据源在方法上使用此注解标注使用哪一个数据源。

`DataSourceAspect`类实现了动态数据源的功能，直接看代码可能会睡着，所以写一个小例子亲自实践下吧。

### 实现思路

1. 自定义一个注解 @DataSource ，将来可以将该注解夹在 Service 层方法或类上面，表示方法或者类中使用的方法都是用某一个数据源。
2. 对于第一步，如果某个方法上面有 @DataSource 注解，那么就将该方法需要使用的数据源名存入到ThrealLocal 中。
3. 自定义切面，在切面中解析 @DataSource 注解，那么就将该方法所需要使用的数据源名称存入到 ThreadLocal中。
4. 最后，当 Mapper 执行的时候，需要 DataSource ，他会自动去 AbstractRoutingDataSource 类中查找所需要的数据源，我们只需要在 AbstractRoutingDataSource 中返回 ThreadLocal 中的值即可。

### 具体实现

https://github.com/Wild-dog-is-me/DynamicDataSourceDemo
