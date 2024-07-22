package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.PositionDTO;
import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Position;
import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
   private final PositionRepository positionRepository;
   private final ModelMapper mapper;

    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper mapper) {
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<String> getAllPositionName() {;
        return positionRepository.findAll().stream()
                .map(position -> position.getPositionName())
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionDTO> getAllPositionsDTOS() {
        List<Position> allPositions = positionRepository.findAll();
        List<PositionDTO> positionsDTOS = new ArrayList<>();

        for (Position position : allPositions) {
            PositionDTO positionDTO = mapper.map(position, PositionDTO.class);
            positionsDTOS.add(positionDTO);
        }

        return positionsDTOS;
    }
}
