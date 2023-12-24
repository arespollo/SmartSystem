import axiosInstance from './axiosInstance';

interface FurnitureData {
  // Define the structure of your furniture data
  name: string;
  type: string;
  status: number;
  // other fields
}

const updateFurniture = async (furnitureData : FurnitureData, token : string) => {
  try {
    const response = await axiosInstance.post('secure/furniture/updateFur', furnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error updating furniture:', error);
    throw error;
  }
};

const deleteFurniture = async (furnitureData : FurnitureData, token : string) => {
  try {
    const response = await axiosInstance.post(`/secure/furniture/delFur`, furnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error deleting furniture:', error);
    throw error;
  }
};

const addFurniture = async (furnitureData : FurnitureData, token: string) => {
  try {
    console.log('furnitureData', furnitureData)
    const response = await axiosInstance.post(`/secure/furniture/addFur`, furnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error adding furniture:', error);
    throw error;
  }
};

const queryFurnitureByUserId = async (token: string) => {
    try {
      const response = await axiosInstance.post(`/secure/furniture/queryFurnitureByUserId`, {}, {
        headers: {
          Authorization: token
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error querying furniture:', error);
      // You can throw the error or handle it according to your needs
      throw error;
    }
  };

export { updateFurniture, deleteFurniture, addFurniture, queryFurnitureByUserId };