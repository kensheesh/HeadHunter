package kg.attractor.headhunter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfo {
    private Integer id;
    private Integer resumeId;
    private Integer ContactTypeId;
    private String content;
}
