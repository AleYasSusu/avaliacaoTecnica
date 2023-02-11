package com.aledev.avaliacaotecnica.v1.model;


import com.aledev.avaliacaotecnica.v1.entity.Staff;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class VotingDto implements Serializable {
	private static final long serialVersionUID = -6641295645471857940L;
	
	private Staff staff;
	private Integer totalVoteYes;
	private Integer totalVoteNo;
	private Integer totalVotes;
	private Integer totalSessions;
}
