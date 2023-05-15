package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.DTO.UpdateMemberDTO;
import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.repository.AddrRepository;

public class AddrService {
    private final AddrRepository addrRepository;

    public AddrService(AddrRepository addrRepository) {
        this.addrRepository = addrRepository;
    }

    /** 주소 저장 기능 */
    public Long AddrJoin(Addr addr) {
        addrRepository.save(addr);
        return addr.getAddrId();
    }

    /** 모든 주소 조회 기능 */
    public List<Addr> findAllAddr() {
        return addrRepository.findAddrAll();
    }

    /** 주소 조회 기능 */
    public Optional<Addr> findAddr(Long memberId) {
        return addrRepository.findAddr_memberId(memberId);
    }

    /** 주소 정보 수정 기능 */
    public Optional<Addr> updateAddr(Addr addr) {
        return addrRepository.update(addr);
    }

    public Addr addrConverter(UpdateMemberDTO updateMemberDTO) {
        Addr addr = new Addr();
        addr.setAddrId(updateMemberDTO.getAddrId());
        addr.setMemberId(updateMemberDTO.getId());
        addr.setMemberName(updateMemberDTO.getName());
        addr.setZipCode(updateMemberDTO.getZipCode());
        addr.setStreetAddr(updateMemberDTO.getStreetAddr());
        addr.setDetailAddr(updateMemberDTO.getDetailAddr());
        return addr;
    }

    /** 주소 정보 삭제 기능 */
    // 위와 같이 memberId;
    public void DropAddr(Long memberId) {
        addrRepository.delete(memberId);
    }

    /** 주소 존재 여부 검증 */
    public Addr verificationAddr(Long memberId) {
        Optional<Addr> addr = addrRepository.findAddr_memberId(memberId);
        if (addr.isPresent()) {
            return addr.get();
        } else {
            return null;
        }
    }

}
