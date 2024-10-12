package com.dbp.winproyect.payment.dto;


import com.dbp.winproyect.payment.domain.Method;
import lombok.Data;

@Data
public class PaymentRequestDto {

    private Long arrangementId;
    private Double amount;
    private String currency;
    private Method method;




//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private Double amount;
//    private String currency;
//    private Date date;
//    private Method method;
//
//    @OneToOne
//    @JoinColumn(name = "arrangement_id")
//    private Arrangement arrangement;
}
