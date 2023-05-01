package ezenstudy.bts.repository;

import ezenstudy.bts.domain.Comcomment;

import java.util.*;


public class MemoryComcommentRepository implements ComcommentRepository {

        private static Map<Long, Comcomment> commentMap = new HashMap<>();
        private static long sequence = 0L;

        @Override
        public Comcomment save(Comcomment comcomment) {
            if (comcomment.getId() == null) {
                comcomment.setId(++sequence);
            }
            commentMap.put(comcomment.getId(), comcomment);
            return comcomment;
        }

        @Override
        public Optional<Comcomment> findById(Long id) {
            return Optional.ofNullable(commentMap.get(id));
        }

        @Override
        public List<Comcomment> findAll() {
            return new ArrayList<>(commentMap.values());
        }

        @Override
        public void deleteById(Long id) {
            commentMap.remove(id);
        }

        @Override
        public long getSequence() {
            return sequence;
        }

}
