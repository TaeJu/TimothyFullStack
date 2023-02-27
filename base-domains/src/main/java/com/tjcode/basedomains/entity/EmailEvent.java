package com.tjcode.basedomains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailEvent {
    String requestName;
    String location;
    String riderInfo;
}

