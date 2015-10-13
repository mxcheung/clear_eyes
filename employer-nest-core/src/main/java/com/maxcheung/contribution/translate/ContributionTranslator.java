package com.maxcheung.contribution.translate;

import java.util.ArrayList;
import java.util.List;

import com.maxcheung.contribution.ContributionSchedule;
import com.maxcheung.contribution.DetailRecord;
import com.maxcheung.contribution.HeaderRecord;
import com.maxcheung.employer.domain.Contribution;

public class ContributionTranslator {

	public static List<Contribution> from(ContributionSchedule contributionSchedule) {
		List<Contribution> contributions = new ArrayList<Contribution>();

		HeaderRecord header = contributionSchedule.getHeaderRecord();
		List<DetailRecord> details = contributionSchedule.getDetailRecord();
		// @formatter:off
		details.stream().
		forEach(detail -> contributions.add(translate(header, detail)));
		// @formatter:on
		return contributions;
	}

	private static Contribution translate(HeaderRecord header, DetailRecord det) {
		Contribution contribution = new Contribution();
		// set the properties based on ContributionSchedule
		contribution.setEmployerReference(header.getNESTEmployerReferenceNumber());
		contribution.setSurname(det.getSurname());
		return contribution;
	}

}
