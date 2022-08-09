package uz.pdp.librarymanagementsystem.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
private Long id ;
private String name;
private String email;
private String role;
private String password;
}

