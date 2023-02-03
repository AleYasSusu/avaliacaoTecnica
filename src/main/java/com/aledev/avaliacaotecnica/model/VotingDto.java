package com.aledev.avaliacaotecnica.model;


import com.aledev.avaliacaotecnica.entity.Staff;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class VotingDto implements Serializable {
	private static final long serialVersionUID = -6641295645471857940L;
	
	private Staff staff;
	private Integer totalVoteYes;
	private Integer totalVoteNo;
	private Integer totalVotes;
	private Integer totalSessions;
}
