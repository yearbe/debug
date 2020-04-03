package app.h_filter;

import java.util.List;

/**
 * 为标准（Criteria）创建一个接口。
 */
public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}