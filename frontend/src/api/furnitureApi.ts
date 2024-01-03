import axiosInstance from './axiosInstance';

interface FurnitureData {
  // Define the structure of your furniture data
  id : number;
  name: string;
  type: string;
  status: number;
  // other fields
}


interface SceneFurniture {
  id: number;
  sceneId: number;
  furId: number;
  furStatus: number;
  furName: string;
  furType: string;
}

interface SceneData {
  id: number;
  name: string;
  userId: number;
  status: number;
  sceneFurnitureList: SceneFurniture[];
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

  const queryHistoryByUserId = async (token: string) => {
    try {
      const response = await axiosInstance.post(`/secure/history/queryHistoryByUserId`, {}, {
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


  // Add Scene
  const addScene = async (sceneData: SceneData, token: string) => {
    try {
      const response = await axiosInstance.post('/secure/scene/addScene', sceneData, {
        headers: {
          Authorization: token
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error adding scene:', error);
      throw error;
    }
  };
  
  

// Delete Scene
const delScene = async (sceneData: SceneData, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/delScene', sceneData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error deleting scene:', error);
    throw error;
  }
};


// Get Scene
const getScene = async (sceneId: number, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/getScene', { id: sceneId }, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error getting scene:', error);
    throw error;
  }
};


// Query Scenes by User ID
const queryScenesByUserId = async (token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/queryScene', {}, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error querying scenes:', error);
    throw error;
  }
};


// Update Scene
const updateScene = async (updatedSceneData: SceneData, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/updateScene', updatedSceneData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error updating scene:', error);
    throw error;
  }
};

// Function to add scene furniture
const addSceneFurniture = async (sceneFurnitureData: SceneFurniture, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/addSceneFurniture', sceneFurnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error adding scene furniture:', error);
    throw error;
  }
};

// Function to update scene furniture
const updateSceneFurniture = async (sceneFurnitureData: SceneFurniture, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/updateSceneFurniture', sceneFurnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error updating scene furniture:', error);
    throw error;
  }
};

// Function to delete scene furniture
const deleteSceneFurniture = async (sceneFurnitureData: SceneFurniture, token: string) => {
  try {
    const response = await axiosInstance.post('/secure/scene/delSceneFurniture', sceneFurnitureData, {
      headers: {
        Authorization: token
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error deleting scene furniture:', error);
    throw error;
  }
};

export { 
  updateFurniture, deleteFurniture, addFurniture, 
  queryFurnitureByUserId , queryHistoryByUserId, queryScenesByUserId, 
  addScene, delScene, getScene,
  addSceneFurniture, deleteSceneFurniture, updateSceneFurniture
};