package br.com.kelvinsantiago.transactionalcontrol.model;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "tb_balance")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SequenceGenerator(name = "balance_seq", allocationSize = 1)
public class Balance {

    @Id
    @GeneratedValue(generator = "balance_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private int value;


}

