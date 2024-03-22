package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactInfo {
    private Integer id;
    private Integer resumeId;
    private Integer ContactTypeId;
    private String content;
}
