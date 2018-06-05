package basicsolutionsoftware.com.empleadoaleatorio.View.Interface;

import basicsolutionsoftware.com.empleadoaleatorio.Domain.Objects.Entidad;

public interface GenerateEmployeeActivityInterface {

    void setNombreAleatorio(String nombreAleatorio);

    void setSegundoNombreAleatorio(String segundoNombreAleatorio);

    void setApellidoPaternoAleatorio(String apellidoPaternoAleatorio);

    void setApellidoMaternoAleatorio(String apellidoMaternoAleatorio);

    void setEdad(String fechaCumpleanios, int edad, boolean cumplidos);

    void setEntidadAleatoria(Entidad entidad);

    void setCurp(String curp);
}
