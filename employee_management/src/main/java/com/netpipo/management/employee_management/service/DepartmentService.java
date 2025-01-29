package com.netpipo.management.employee_management.service;

import com.netpipo.management.employee_management.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.manage.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepo;


    public Department createDepartment(Department department) {

        return departmentRepo.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public List<Department> searchDepartmentByName(String name) {

        return departmentRepo.findByNameContainingIgnoreCase(name);
    }

    public Department getDepartmentByName(String name) {

        return departmentRepo.findByName(name).orElseThrow(
                ()-> new RuntimeException("Department not found")
        );
    }

    public Department updateDepartment(
            long id,
            Department updatedDepartment
    ) {
        return departmentRepo.findById(id)
                .map(
                        department -> {
                            department.setName(updatedDepartment.getName());
                        return departmentRepo.save(department);
                        }
                ).orElseThrow(
                        ()-> new RuntimeException("Department Not Found! ")
                );
    }

    public void deleteDepartment (long id) {
        departmentRepo.deleteById(id);
    }

}
