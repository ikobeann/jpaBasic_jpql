package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // persistence.xml 에 unit-name
        // Db랑 연결도 해주고 ㄷ ㅏ 해줌
        // 한 번만 생성해야 한다! DB당 하나만.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드 간 절대 공유하면 안됨. 장애 발생의 요인. 꼭 꼭 close 해줘야함.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Team team = new Team();
            //team.setName("teamA");

            Member member = new Member();
            member.setUsername("member1");
            member.getTeam().setName("teambbb");
            member.setMemberType(MemberType.ADMIN);

            //member.setTeam(team);

            em.persist(member);
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
/*

            TypedQuery<Member> query =
                    em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);
            query.setParameter("username","member1");
            Member singleResult = query.getSingleResult();
            System.out.println("singleResult.getUsername() = " + singleResult.getUsername());

            
            // 체인 이용
            Member chainResult = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
                    .setParameter("username","member1")
                    .getSingleResult();
            System.out.println("chainResult = " + chainResult);

            // m 뒤에 띄어쓰기 안했더니 오류남...ㅅㅂ
            String query2 = "select m.username, 'hello', true From Member m " +
                            "where m.type = :usertType";
            List<Object[]> result =  em.createQuery(query2)
                        .setParameter("userType", MemberType.ADMIN).getResultList();
            
            for(Object[] o : result) {
                System.out.println("o[0] = " + o[0]);
                System.out.println("o[1] = " + o[1]);
                System.out.println("o[2] = " + o[2]);
            }

                 String query3 =
                         "select " +
                                 "case when m.age <= 10 then '학생요금'" +
                                 "     when m.age >= 10 then '경로요금'" +
                                 "     else '일반요금'" +
                                 "end " +
                "from Member m";
                 List<String> caseResult = em.createQuery(query3, String.class)
                         .getResultList();

                 for (String s : caseResult) {
                     System.out.println("s = " + s);
                 }
*/

        tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

}
