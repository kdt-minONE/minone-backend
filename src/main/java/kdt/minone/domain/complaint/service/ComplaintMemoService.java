package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.ComplaintMemoDetailResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintMemo;
import kdt.minone.domain.complaint.repository.ComplaintMemoRepository;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.user.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintMemoService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMemoRepository complaintMemoRepository;

    @Transactional
    public ComplaintMemoDetailResDto createMemo(Long complaintId, Employee employee, String title, String content) {
        Complaint complaint = complaintRepository.findByIdOrElseThrow(complaintId);

        ComplaintMemo memo = new ComplaintMemo(complaint, employee, title, content);
        complaintMemoRepository.save(memo);

        return new ComplaintMemoDetailResDto(memo);
    }

    @Transactional(readOnly = true)
    public List<ComplaintMemoDetailResDto> findAll(Long complaintId) {
        List<ComplaintMemo> memos = complaintMemoRepository.findAllByComplaintId(complaintId);

        return memos.stream()
                .map(ComplaintMemoDetailResDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ComplaintMemoDetailResDto findMemoById(Long complaintId, Long memoId) {
        ComplaintMemo memo = complaintMemoRepository.findByIdAndComplaintId(memoId, complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        return new ComplaintMemoDetailResDto(memo);
    }

    @Transactional
    public ComplaintMemoDetailResDto updateMemoById(Long complaintId, Long memoId, String title, String content) {
        ComplaintMemo memo = complaintMemoRepository.findByIdAndComplaintId(memoId, complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        memo.updateMemo(title, content);

        return new ComplaintMemoDetailResDto(memo);
    }

    @Transactional
    public void deleteMemoById(Long complaintId, Long memoId) {
        ComplaintMemo memo = complaintMemoRepository.findByIdAndComplaintId(memoId, complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        complaintMemoRepository.delete(memo);
    }
}
