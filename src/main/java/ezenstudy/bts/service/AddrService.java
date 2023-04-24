package ezenstudy.bts.service;

import java.util.Optional;

import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.repository.AddrRepository;
import ezenstudy.bts.repository.MemberRepository;

public class AddrService {
    private AddrRepository addrRepository;
    private MemberRepository memberRepository;

    public AddrService(AddrRepository addrRepository, MemberRepository memberRepository) {
        this.addrRepository = addrRepository;
        this.memberRepository = memberRepository;
    }

    /** 주소 저장 기능 */
    public Long AddrSave(Addr addr) {
        addrRepository.save(addr);
        return addr.getAddrId();
    }

    /** 주소 조회 기능 */
    // 서비스에는 본인 레파지토리만. 섞는거는 컨트롤러.
    // memberId;
    public Addr FindAddr(String memberName) {
        Optional<Addr> memberAddr = addrRepository.findAddrName(memberName);
        Optional<Member> members = memberRepository.findAll().stream().filter(m -> m.getName().equals(memberName))
                .findAny();
        if (members.isPresent()) {
            Addr addr = memberAddr.get();
            Member member = members.get();
            if (addr.getMemberId() == member.getId()) {
                return addr;
            }
        }
        return null;
    }

    /** 주소 삭제 기능 */
    // 위와 같이 memberId;
    public String DropAddr(String memberName) {
        Optional<Addr> memberAddr = addrRepository.findAddrName(memberName);
        if (addrRepository.findAddrName(memberName).isPresent()) {
            Addr addr = memberAddr.get();
            addrRepository.deleteAll(addr.getAddrId());
            return "모든 주소가 삭제되었습니다.";
        } else {
            return "저장된 주소가 없습니다.";
        }
    }

}
