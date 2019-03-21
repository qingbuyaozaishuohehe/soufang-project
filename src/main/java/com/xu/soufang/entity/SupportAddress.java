package com.xu.soufang.entity;


import lombok.Data;
import javax.persistence.*;


@Table
@Entity
@Data
public class SupportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "belong_to")
    private String belongTo;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "cn_name")
    private String cnName;

    @Column(name = "level")
    private String level;

    public enum Level {

        /**
         * 城市
         */
        CITY("city"),
        /**
         * 区域
         */
        REGION("region");

        private String value;

        Level(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Level of(String value) {
            for (Level level : Level.values()) {
                if (level.getValue().equals(value)) {
                    return level;
                }
            }
            throw new IllegalArgumentException();
        }


    }
}