package basicsolutionsoftware.com.empleadoaleatorio.View.Interface;

import basicsolutionsoftware.com.empleadoaleatorio.Domain.Objects.Entidad;

public interface GenerateEmployeeActivityInterface {

    void setNombreAleatorio(String object);

    void setSegundoNombreAleatorio(String object);

    void setApellidoPaternoAleatorio(String object);

    void setApellidoMaternoAleatorio(String object);

    void setEdad(String fechaCumpleanios, int edad, boolean cumplidos);

    void setSexo(int randomInt);

    void setEntidadAleatoria(Entidad entidad);

    void setCurp(String curp);
}
