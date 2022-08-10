package uz.pdp.librarymanagementsystem.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {
    private Long id;
    private String user;
    private String data;
    private String book;
    private String status;

}
