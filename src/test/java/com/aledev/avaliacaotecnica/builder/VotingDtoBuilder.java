package com.aledev.avaliacaotecnica.builder;

import com.aledev.avaliacaotecnica.v1.model.VotingDto;

import static com.aledev.avaliacaotecnica.builder.StaffBuilder.buildStaffDefault;

public class VotingDtoBuilder {

    private VotingDto votingDto;

    public static VotingDtoBuilder buildVotingDtoDefault() {
        VotingDtoBuilder builder = new VotingDtoBuilder();
        builder.votingDto = VotingDto.builder()
                .totalVoteNo(0)
                .staff(buildStaffDefault().build())
                .totalSessions(10)
                .totalVoteYes(1)
                .totalVoteNo(0)
                .totalVotes(1)
                .build();
        return builder;
    }

    public VotingDto build() {
        return votingDto;
    }
}
