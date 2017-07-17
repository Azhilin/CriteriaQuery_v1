package base;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.xml.transform.sax.SAXSource;
import java.util.List;

/**
 * Created by azhilin on 02.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();

//        entityManager.getTransaction().begin();
//        Human human1 = new Human();
//        human1.setName("Vasyl");
//        human1.setSurname("Vasylenko");
//        Human human2 = new Human();
//        human2.setName("Petro");
//        human2.setSurname("Petrenko");
//        Human human3 = new Human();
//        human3.setName("Dmytro");
//        human3.setSurname("Dmytrenko");
//        entityManager.persist(human1);
//        entityManager.persist(human2);
//        entityManager.persist(human3);
//        entityManager.getTransaction().commit();
//
//        entityManager.getTransaction().begin();
//        Bank bank1 = new Bank();
//        bank1.setName("Kreschatyk");
//        List<Human> humans1  = new ArrayList<Human>();
//        humans1.add(human1);
//        humans1.add(human2);
//        bank1.setHumans(humans1);
//
//        Bank bank2 = new Bank();
//        bank2.setName("Privat");
//        List<Human> humans2  = new ArrayList<Human>();
//
//        humans2.add(human3);
//        bank1.setHumans(humans2);
//        human1.setBank(bank1);
//        human2.setBank(bank1);
//        human3.setBank(bank2);
//        entityManager.persist(bank1);
//        entityManager.persist(bank2);
//        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);
//        Root<Bank> bankRoot = criteriaQuery.bankRoot(Bank.class);
//        criteriaQuery.select(bankRoot);
//
//        ParameterExpression<String> bankName = criteriaBuilder.parameter(String.class);
//        criteriaQuery.where(criteriaBuilder.equal(bankRoot.get(Bank_.name), bankName));
//
//        TypedQuery<Bank> bankTypedQuery = entityManager.createQuery(criteriaQuery).setParameter(bankName, "Privat");
//
//        Bank b = bankTypedQuery.getSingleResult();
//
//        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);
//        Root<Bank> bankRoot = criteriaQuery.bankRoot(Bank.class);
//        criteriaQuery.select(bankRoot);
//
//        ParameterExpression<String> clientName = criteriaBuilder.parameter(String.class);
//        Join<Bank, Human> bankHumanJoin = bankRoot.join(Bank_.humans);
//        criteriaQuery.where(criteriaBuilder.equal(bankHumanJoin.get(Human_.name), clientName));
//
//        TypedQuery<Bank> bankTypedQuery = entityManager.createQuery(criteriaQuery).setParameter(clientName, "Vasyl");
//
//        List<Bank> banks = bankTypedQuery.getResultList();
//
//        for (Bank b: banks) {
//            System.out.println(b.getName());
//        }
//
//        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
//        CriteriaBuilder criteriaBuilder2 = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Bank> criteriaQuery2 = criteriaBuilder2.createQuery(Bank.class);
//        Root<Bank> rootBank2 = criteriaQuery2.bankRoot(Bank.class);
//        criteriaQuery2.select(rootBank2);
//
//
//        Path<Human> path = rootBank2.get(Bank_.humans);
//        Subquery<Human> subquery = criteriaQuery2.subquery(Human.class);
//        Root<Human> humanRoot2 = subquery.bankRoot(Human.class);
//        subquery.select(humanRoot2.get(Human_.bank));
//
//        ParameterExpression<String> clientName2 = criteriaBuilder2.parameter(String.class);
//        subquery.where(criteriaBuilder2.equal(humanRoot2.get(Human_.name), clientName2));
//
//        criteriaQuery2.where(criteriaBuilder2.in(path).value(subquery));
//
//
//        entityManager.getTransaction().commit();


//        System.out.println(b.getId());



        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder2 = entityManager.getCriteriaBuilder();

        CriteriaQuery<Bank> criteriaQuery2 = criteriaBuilder2.createQuery(Bank.class);
        Root<Bank> rootBank2 = criteriaQuery2.from(Bank.class);
//
        Subquery<Integer> subquery = criteriaQuery2.subquery(Integer.class);
        Root<Human> humanRoot2 = subquery.from(Human.class);
        Join<Human, Bank> join1 = humanRoot2.join(Human_.bank);
        subquery.select(join1.get(Bank_.id)).where(criteriaBuilder2.
                equal(humanRoot2.get(Human_.name),
                        criteriaBuilder2.parameter(String.class, "name")));

        criteriaQuery2.select(rootBank2).where(criteriaBuilder2
                .in(rootBank2.get(Bank_.id)).value(subquery));
        TypedQuery<Bank> bankTypedQuery2 =
                entityManager.createQuery(criteriaQuery2)
                        .setParameter("name", "Vasyl");
        final List<Bank> banks1 = bankTypedQuery2.getResultList();
        for(Bank b1: banks1){
            System.out.println(b1.getName());
        }
        entityManager.getTransaction().commit();
//        }

        entityManager.close();
        sessionFactory.close();
    }
}