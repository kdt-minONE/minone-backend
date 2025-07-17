package kdt.minone.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kdt.minone.domain.department.entity.QDepartment;
import kdt.minone.domain.user.dto.EmployeeListResDto;
import kdt.minone.domain.user.dto.QEmployeeListResDto;
import kdt.minone.domain.user.entity.QEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<EmployeeListResDto> findAllWithDepartment(Pageable pageable) {
        QEmployee employee = QEmployee.employee;
        QDepartment department = QDepartment.department;

        List<EmployeeListResDto> employees = jpaQueryFactory
                .select(new QEmployeeListResDto(
                        employee.id,
                        department.id,
                        department.name,
                        employee.email,
                        employee.name,
                        employee.phone,
                        employee.role.stringValue()
                ))
                .from(employee)
                .leftJoin(department).on(employee.department.id.eq(department.id)).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(jpaQueryFactory.select(employee.count())
                .from(employee)
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(employees, pageable, total);
    }
}
