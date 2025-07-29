package kdt.minone.domain.complaint.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kdt.minone.domain.complaint.dto.CitizenComplaintListResDto;
import kdt.minone.domain.complaint.dto.QCitizenComplaintListResDto;
import kdt.minone.domain.complaint.entity.QComplaint;
import kdt.minone.domain.complaint.entity.QComplaintResult;
import kdt.minone.domain.department.entity.QDepartment;
import kdt.minone.domain.user.entity.QEmployee;
import kdt.minone.global.enums.ComplaintStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomComplaintRepositoryImpl implements CustomComplaintRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CitizenComplaintListResDto> searchComplaintsByUser(Pageable pageable, Long userId, String complaintNo, String content, String status) {
        QComplaint complaint = QComplaint.complaint;
        QDepartment department = QDepartment.department;
        QEmployee employee = QEmployee.employee;
        QComplaintResult result = QComplaintResult.complaintResult;

        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(complaint.citizen.id.eq(userId));

        if (complaintNo != null) {
            conditions.and(complaint.complaintNo.contains(complaintNo));
        }

        if (content != null) {
            conditions.and(complaint.content.contains(content));
        }

        if (status != null) {
            ComplaintStatus enumStatus = ComplaintStatus.of(status.toUpperCase());
            conditions.and(complaint.status.eq(enumStatus));
        }

        List<CitizenComplaintListResDto> complaints = jpaQueryFactory
                .select(new QCitizenComplaintListResDto(
                        complaint.id,
                        complaint.complaintNo,
                        department.id,
                        department.name,
                        employee.id,
                        employee.name,
                        complaint.address,
                        complaint.title,
                        complaint.content,
                        complaint.status.stringValue(),
                        result.content,
                        complaint.createdAt,
                        complaint.updatedAt
                ))
                .from(complaint)
                .innerJoin(department).on(complaint.department.id.eq(department.id)).fetchJoin()
                .innerJoin(employee).on(complaint.employee.id.eq(employee.id)).fetchJoin()
                .innerJoin(result).on(complaint.id.eq(result.complaint.id)).fetchJoin()
                .where(conditions)
                .orderBy(complaint.createdAt.desc(), complaint.complaintNo.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(jpaQueryFactory.select(complaint.count())
                .from(complaint)
                .where(conditions)
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(complaints, pageable, total);
    }
}
