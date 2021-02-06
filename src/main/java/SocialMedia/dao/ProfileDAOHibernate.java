package SocialMedia.dao;

import SocialMedia.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//public class ProfileDAOHibernate {
//
//    private static SessionFactory factory = null;
//
//
//    private static Session session = null;
//
//    public List<Profile> index() {
//        List<Profile> usersProfile;
//        factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Profile.class)
//                .buildSessionFactory();
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            usersProfile = session.createQuery("from users").getResultList();
//            session.getTransaction().commit();
//        } finally {
//            session.close();
//            factory.close();
//        }
//        return usersProfile;
//    }
//
//    public Profile show(int id) {
//        factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Profile.class)
//                .buildSessionFactory();
//        Profile profile = null;
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            profile = session.get(Profile.class, id);
//            session.getTransaction().commit();
//        } finally {
//            session.close();
//            factory.close();
//        }
//        return profile;
//    }
//
//    public void save(Profile profile) {
//        factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Profile.class)
//                .buildSessionFactory();
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            session.save(profile);
//            session.getTransaction().commit();
//        } finally {
//            session.close();
//            factory.close();
//        }
//    }
//
//    public void update(int id, Profile updateProfile) {
//        factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Profile.class)
//                .buildSessionFactory();
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Profile profile = session.get(Profile.class,id);
//            profile.setName(updateProfile.getName());
//            profile.setAge(updateProfile.getAge());
//            profile.setEmail(updateProfile.getEmail());
//            session.getTransaction().commit();
//
//        } finally {
//            session.close();
//            factory.close();
//        }
//    }
//
//    public void delete(int id) {
//        factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Profile.class)
//                .buildSessionFactory();
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Profile profile = session.get(Profile.class, id);
//            session.delete(profile);
//            session.getTransaction().commit();
//        } finally {
//            session.close();
//            factory.close();
//        }
//
//    }
//}
