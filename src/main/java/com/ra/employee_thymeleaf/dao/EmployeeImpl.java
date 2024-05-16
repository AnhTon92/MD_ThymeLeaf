package com.ra.employee_thymeleaf.dao;

import com.ra.employee_thymeleaf.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeImpl implements IEmployee {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployees() {
        Session session = sessionFactory.openSession();
        List<Employee> list = new ArrayList<>();
        try {
            list = session.createQuery("select e from Employee e", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;

    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        Session session = sessionFactory.openSession();
        try {
            Employee employee = session.find(Employee.class, employeeId);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public boolean insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();

        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

        @Override
        public boolean deleteEmployee (Integer employeeId){
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.delete(getEmployeeById(employeeId));
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
            return false;
        }

        @Override
        public List<Employee> getEmployeeByName (String name){
            Session session = sessionFactory.openSession();
            List<Employee> list = new ArrayList<>();
            try {
                list = session
                        .createQuery("select e from Employee e where e.name like concat('%',:search,'%') ", Employee.class)
                        .setParameter("search",name)
                        .getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
            return list;

        }

    @Override
    public Boolean existByPhone(String phone) {
        Session session = sessionFactory.openSession();
        Long count=0L;
        try {
            count = (Long) session.createQuery("select count(e) from Employee e where e.phone like :phone").setParameter("phone",phone).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count > 0;
    }
}
