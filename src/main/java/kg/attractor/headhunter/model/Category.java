package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
}
