package com.ra.employee_thymeleaf.model;
import com.ra.employee_thymeleaf.validation.PhoneExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    //    	id : int (id tự tăng)
//	name : String
//	address : String
//	dateOfBirth : Date
//	phone : String
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tương ứng với auto_increment
    @NotEmpty(message = "Employee id is empty")
    @NotNull(message = "Employee id is null")
    private Integer employeeId;
    @Column(name = "employee_name")
    @NotEmpty(message = "Employee name is empty")
    private String name;

    @Column(name = "employee_add")
    @NotEmpty(message = "Employee address is empty")
    private String address;
    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth is empty")
    @Past(message = "Date of birth is not valid")
    private Date dob;
    @Column(name = "phone")
//    @PhoneExist(message = "số điện thoại đã bị trùng")
    @NotEmpty(message = "Employee phone is empty")
    private String phone;

}
