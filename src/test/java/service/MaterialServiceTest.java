package service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MaterialServiceTest {


    /*@Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialService materialService;

    @Test
    public void testCreateMaterial() {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setType("wood");

        Material createdMaterial = materialService.create(materialDto);

        assertNotNull(createdMaterial.getId());
        assertEquals(materialDto.getType(), createdMaterial.getType());

        materialRepository.deleteById(createdMaterial.getId());
    }

    @Test
    public void testGetByIdWithExistingMaterial() {
        Material existingMaterial = new Material();
        existingMaterial.setType("stone");
        materialRepository.save(existingMaterial);

        Material foundMaterial = materialService.getById(existingMaterial.getId());
        assertEquals(existingMaterial.getType(), foundMaterial.getType());


        materialRepository.delete(existingMaterial);
    }

    private void assertEquals(String type, String type1) {
    }


    @Test
    public void testRemoveExistingMaterial() {
        Material existingMaterial = new Material();
        existingMaterial.setType("plastic");
        materialRepository.save(existingMaterial);

        materialService.remove(existingMaterial.getId());

        assertFalse(materialRepository.existsById(existingMaterial.getId()));
    }


    @Test
    public void testUpdateExistingMaterial() {
        Material existingMaterial = new Material();
        existingMaterial.setType("metal");
        materialRepository.save(existingMaterial);

        MaterialDto updatedMaterialDto = new MaterialDto();
        updatedMaterialDto.setId(existingMaterial.getId());
        updatedMaterialDto.setType("gold");

        materialService.update(updatedMaterialDto);

        Material updatedMaterial = materialRepository.findById(existingMaterial.getId()).orElse(null);
        assertNotNull(updatedMaterial);
        assertEquals(updatedMaterialDto.getType(), updatedMaterial.getType());

        materialRepository.delete(existingMaterial);
    }*/


}
